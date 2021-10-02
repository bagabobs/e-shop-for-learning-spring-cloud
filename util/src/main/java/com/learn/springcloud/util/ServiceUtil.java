package com.learn.springcloud.util;

import com.learn.springcloud.util.exceptions.NotFoundException;
import com.learn.springcloud.util.exceptions.ServiceAddressNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
@Slf4j
public class ServiceUtil {
    private final String port;
    private String serviceAddress = null;

    @Autowired
    public ServiceUtil(@Value("${server.port}") String port) {
        this.port = port;
    }

    public String getServiceAddress() throws ServiceAddressNotFoundException {
        try {
            if(serviceAddress == null) {
                serviceAddress = findMyHostName() + "/" + findMyIpAddress() + ":" + port;
            }
            return serviceAddress;
        } catch(Exception e) {
            throw new ServiceAddressNotFoundException("Error: when get Address", e);
        }
    }

    private String findMyHostName() throws Exception {
        return InetAddress.getLocalHost().getHostAddress();
    }

    private String findMyIpAddress() throws Exception {
        return InetAddress.getLocalHost().getHostAddress();
    }
}
