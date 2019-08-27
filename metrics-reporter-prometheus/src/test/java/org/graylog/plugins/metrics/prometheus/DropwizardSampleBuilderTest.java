/**
 * This file is part of Graylog Metrics Prometheus Reporter Plugin.
 *
 * Graylog Metrics Prometheus Reporter Plugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog Metrics Prometheus Reporter Plugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog Metrics Prometheus Reporter Plugin.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog.plugins.metrics.prometheus;

import com.google.common.primitives.Ints;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Collector.MetricFamilySamples.Sample;
import io.prometheus.client.exporter.PushGateway;

import org.graylog2.plugin.streams.Stream;
import org.graylog2.plugin.streams.StreamRule;
import org.graylog2.plugin.streams.StreamRuleType;
import org.graylog2.streams.StreamRuleService;
import org.graylog2.streams.StreamService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.emptyList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static java.util.Arrays.*;

public class DropwizardSampleBuilderTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock(answer = Answers.RETURNS_MOCKS)
    private StreamService streamService;
    @Mock(answer = Answers.RETURNS_MOCKS)
    private Stream stream;
    @Mock(answer = Answers.RETURNS_MOCKS)
    private StreamRule streamRule;

    @Mock(answer = Answers.RETURNS_MOCKS)
    private StreamRuleService streamRuleService;

    private DropwizardSampleBuilder sampleBuilder;
    @Before
    public void setUp() throws Exception {
        sampleBuilder = new DropwizardSampleBuilder(streamService,streamRuleService);
    }

    @Test
    public void doTestStream() throws Exception {
        when(streamService.load(anyString())).thenReturn(stream);
        when(stream.getTitle()).thenReturn("foo");
        when(stream.getIndexSetId()).thenReturn("42");

        Sample s = sampleBuilder.createSample("org.graylog2.plugin.streams.Stream.58934a5e92493a6b7f383fea.incomingMessages.1-sec-rate", "", null, null, 2.0);

        assertEquals(new Sample(
           "org_graylog2_plugin_streams_Stream:incomingMessages_1_sec_rate",
           Arrays.asList("id", "stream-title", "index-set-id"),
           Arrays.asList("58934a5e92493a6b7f383fea","foo", "42"),
           2.0
        ),s);
    }

    @Test
    public void doTestStreamRule() throws Exception {
        when(streamRuleService.load(anyString())).thenReturn(streamRule);
        when(streamRule.getStreamId()).thenReturn("x");
        when(streamRule.getType()).thenReturn(StreamRuleType.PRESENCE);

        when(streamService.load("x")).thenReturn(stream);
        when(stream.getTitle()).thenReturn("foo");
        when(stream.getIndexSetId()).thenReturn("42");

        Sample s = sampleBuilder.createSample("org.graylog2.plugin.streams.StreamRule.58934a5e92493a6b7f384059.executionTime", "", asList("quantile"), asList("0.5"), 1.0050000000000001E-6);

        assertEquals(new Sample(
           "org_graylog2_plugin_streams_StreamRule:executionTime",
           Arrays.asList("quantile", "id", "rule-type","stream-id", "stream-title", "index-set-id"),
           Arrays.asList("0.5","58934a5e92493a6b7f384059","PRESENCE", "x", "foo", "42"),
           1.0050000000000001E-6
        ),s);

    }

    @Test
    public void doTestMetricWithoutID() throws Exception {
        when(streamRuleService.load(anyString())).thenReturn(streamRule);
        when(streamRule.getStreamId()).thenReturn("x");
        when(streamRule.getType()).thenReturn(StreamRuleType.PRESENCE);

        when(streamService.load("x")).thenReturn(stream);
        when(stream.getTitle()).thenReturn("foo");
        when(stream.getIndexSetId()).thenReturn("42");

        Sample s = sampleBuilder.createSample("jvm.threads.timed.waiting.count", "",emptyList(), emptyList(), 66.0);

        assertEquals(new Sample(
           "jvm_threads_timed_waiting_count",
           emptyList(),
           emptyList(),
           66.0
        ),s);
    }

    @Test
    public void doTestNonStreamIDMetric() throws Exception {
        Sample s = sampleBuilder.createSample("org.graylog2.inputs.extractors.RegexExtractor.regex.203bfc45-0acd-4a82-b4fd-c3710fdce62d.conditionHits", "",emptyList(), emptyList(),  1906140.0);

        assertEquals(new Sample(
           "org_graylog2_inputs_extractors_RegexExtractor_regex:conditionHits",
           asList("id"),
           asList("203bfc45-0acd-4a82-b4fd-c3710fdce62d"),
           1906140.0
        ),s);
    }
}