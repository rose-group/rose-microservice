/*
 * Copyright © 2025 rosestack.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.rose.redis.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZkCuratorConfig {

    @Value("${zk.curator.retryCount}")
    private int retryCount;

    @Value("${zk.curator.elapsedTimeMs}")
    private int elapsedTimeMs;

    @Value("${zk.curator.connectUrl}")
    private String connectUrl;

    @Value("${zk.curator.sessionTimeOutMs}")
    private int sessionTimeOutMs;

    @Value("${zk.curator.connectionTimeOutMs}")
    private int connectionTimeOutMs;

    @Bean(initMethod = "start", destroyMethod = "close")
    public CuratorFramework curatorFramework() {
        return CuratorFrameworkFactory.newClient(
                connectUrl, sessionTimeOutMs, connectionTimeOutMs, new RetryNTimes(retryCount, elapsedTimeMs));
    }

    @Bean
    public ZkLock zkLock() {
        return new ZkLockImpl(curatorFramework());
    }
}
