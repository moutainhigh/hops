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

package org.apache.hadoop.yarn.server.nodemanager.containermanager.loghandler.event;

import java.util.Map;

import org.apache.hadoop.security.Credentials;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.ApplicationAccessType;
import org.apache.hadoop.yarn.api.records.LogAggregationContext;

public class LogHandlerAppStartedEvent extends LogHandlerEvent {

  private final ApplicationId applicationId;
  private final String user;
  private final String userFolder;
  private final Credentials credentials;
  private final Map<ApplicationAccessType, String> appAcls;
  private final LogAggregationContext logAggregationContext;

  public LogHandlerAppStartedEvent(ApplicationId appId, String user,
      Credentials credentials, Map<ApplicationAccessType, String> appAcls, String userFolder) {
    this(appId, user, credentials, appAcls, null, userFolder);
  }

  public LogHandlerAppStartedEvent(ApplicationId appId, String user,
      Credentials credentials, Map<ApplicationAccessType, String> appAcls,
      LogAggregationContext logAggregationContext, String userFolder) {
    super(LogHandlerEventType.APPLICATION_STARTED);
    this.applicationId = appId;
    this.user = user;
    this.userFolder = userFolder;
    this.credentials = credentials;
    this.appAcls = appAcls;
    this.logAggregationContext = logAggregationContext;
  }

  public ApplicationId getApplicationId() {
    return this.applicationId;
  }

  public Credentials getCredentials() {
    return this.credentials;
  }

  public String getUser() {
    return this.user;
  }

  public String getUserFolder() {
    return this.userFolder;
  }
  
  public Map<ApplicationAccessType, String> getApplicationAcls() {
    return this.appAcls;
  }

  public LogAggregationContext getLogAggregationContext() {
    return this.logAggregationContext;
  }
}
