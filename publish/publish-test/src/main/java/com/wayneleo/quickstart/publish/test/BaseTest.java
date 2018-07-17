package com.wayneleo.quickstart.publish.test;

import com.wayneleo.quickstart.framework.core.conf.Application;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith( SpringInstanceTestClassRunner.class )
@SpringBootTest( classes = Application.class )
public abstract class BaseTest implements InstanceTestClassListener {}
