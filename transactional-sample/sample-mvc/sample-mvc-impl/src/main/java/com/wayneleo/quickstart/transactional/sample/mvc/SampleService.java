package com.wayneleo.quickstart.transactional.sample.mvc;

import org.springframework.stereotype.Service;
import com.wayneleo.quickstart.framework.base.BaseService;

@Service
public class SampleService extends BaseService implements ISampleService { // ISampleService是个接口，应该写在service-api里面
    public SampleResp doBusiness( ParamEntity param ) { // ParamEntity 是个实体类，应该写在service-api里面
        // 处理业务...
        return new SampleResp(); // SampleResp 是个VO类，应该写在service-api里面
    }
}
