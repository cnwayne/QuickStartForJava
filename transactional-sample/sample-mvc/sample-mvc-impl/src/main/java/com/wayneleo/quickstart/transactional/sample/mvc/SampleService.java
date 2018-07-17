package com.wayneleo.quickstart.transactional.sample.mvc;

import com.wayneleo.quickstart.framework.base.BaseService;

public class SampleService extends BaseService implements ISampleService { // ISampleService是个接口，应该写在service-api里面
    private static final long serialVersionUID = 6046909713505643651L;

    public SampleResp doBusiness( ParamEntity param ) { // ParamEntity 是个实体类，应该写在service-api里面
        // 处理业务...
        return new SampleResp(); // SampleResp 是个VO类，应该写在service-api里面
    }
}
