package com.wayneleo.quickstart.publish.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import com.wayneleo.quickstart.framework.core.conf.Application;

@RunWith( SpringInstanceTestClassRunner.class )
@SpringBootTest( classes = Application.class )
public abstract class BaseTest implements InstanceTestClassListener {}
