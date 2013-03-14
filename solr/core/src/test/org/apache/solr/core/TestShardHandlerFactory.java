/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.solr.core;

import org.apache.solr.SolrTestCaseJ4;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.handler.component.ShardHandlerFactory;

import java.io.File;

/**
 * Tests specifying a custom ShardHandlerFactory
 */
public class TestShardHandlerFactory extends SolrTestCaseJ4 {

  public void testXML() throws Exception {
    CoreContainer cc = new CoreContainer(TEST_HOME());
    cc.load(TEST_HOME(), new File(TEST_HOME(), "solr-shardhandler.xml"));
    ShardHandlerFactory factory = cc.getShardHandlerFactory();
    assertTrue(factory instanceof MockShardHandlerFactory);
    NamedList args = ((MockShardHandlerFactory)factory).args;
    assertEquals("myMagicRequiredValue", args.get("myMagicRequiredParameter"));
    factory.close();
    cc.shutdown();
  }
  
  public void testProperties() throws Exception {
    CoreContainer cc = new CoreContainer(TEST_HOME());
    cc.load(TEST_HOME(), new File(TEST_HOME(), "solr-shardhandler.properties"));
    ShardHandlerFactory factory = cc.getShardHandlerFactory();
    assertTrue(factory instanceof MockShardHandlerFactory);
    NamedList args = ((MockShardHandlerFactory)factory).args;
    assertEquals("myMagicRequiredValue", args.get("myMagicRequiredParameter"));
    factory.close();
    cc.shutdown();
  }
}
