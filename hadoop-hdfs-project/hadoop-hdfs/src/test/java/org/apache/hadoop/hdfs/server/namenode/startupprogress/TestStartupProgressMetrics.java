/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hdfs.server.namenode.startupprogress;

import static org.apache.hadoop.hdfs.server.namenode.startupprogress.Phase.*;
import static org.apache.hadoop.hdfs.server.namenode.startupprogress.StartupProgressTestHelper.*;
import static org.apache.hadoop.hdfs.server.namenode.startupprogress.StepType.*;
import static org.apache.hadoop.test.MetricsAsserts.*;
import static org.junit.Assert.*;

import org.apache.hadoop.metrics2.MetricsRecordBuilder;
import org.junit.Before;
import org.junit.Test;

public class TestStartupProgressMetrics {

  private StartupProgress startupProgress;
  private StartupProgressMetrics metrics;

  @Before
  public void setUp() {
    mockMetricsSystem();
    startupProgress = new StartupProgress();
    metrics = new StartupProgressMetrics(startupProgress);
  }

  @Test
  public void testInitialState() {
    MetricsRecordBuilder builder = getMetrics(metrics, true);
    assertCounter("ElapsedTime", 0L, builder);
    assertGauge("PercentComplete", 0.0f, builder);
    assertCounter("SafeModeCount", 0L, builder);
    assertCounter("SafeModeElapsedTime", 0L, builder);
    assertCounter("SafeModeTotal", 0L, builder);
    assertGauge("SafeModePercentComplete", 0.0f, builder);
  }

  @Test
  public void testFinalState() {
    setStartupProgressForFinalState(startupProgress);

    MetricsRecordBuilder builder = getMetrics(metrics, true);
    assertTrue(getLongCounter("ElapsedTime", builder) >= 0L);
    assertGauge("PercentComplete", 1.0f, builder);
    assertCounter("SafeModeCount", 400L, builder);
    assertTrue(getLongCounter("SafeModeElapsedTime", builder) >= 0L);
    assertCounter("SafeModeTotal", 400L, builder);
    assertGauge("SafeModePercentComplete", 1.0f, builder);
  }
}
