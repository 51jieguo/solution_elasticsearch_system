package cn.yangtengfei.config;


import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;

@Configuration
@EnableElasticsearchRepositories(basePackages = "cn.yangtengfei.repository.employee", elasticsearchTemplateRef="employeeElasticsearchTemplate")
public class EmoployeeDataConfig {

    @Bean(name = "employeeEsProperties")
    @ConfigurationProperties(prefix = "spring.data.elasticsearch.employee")
    public ElasticsearchProperties employeeElasticsearchProperties() {
        return new ElasticsearchProperties();
    }

    @Bean(name = "employeeElasticsearchTemplate")
    public ElasticsearchTemplate danmuMongoTemplate() throws Exception {
        //设置集群名称
        Settings settings = Settings.builder().put("cluster.name", "my-application").build();// 集群名
        //创建client
        ElasticsearchProperties elasticsearchProperties = employeeElasticsearchProperties();
        String [] array = elasticsearchProperties.getClusterNodes().split(":");
        String hostName =array[0];
        String port = array[1];

        TransportClient transportClient  = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostName), Integer.parseInt(port)));
        ElasticsearchTemplate elasticsearchTemplate = new ElasticsearchTemplate(transportClient);
        return elasticsearchTemplate;
    }
}
