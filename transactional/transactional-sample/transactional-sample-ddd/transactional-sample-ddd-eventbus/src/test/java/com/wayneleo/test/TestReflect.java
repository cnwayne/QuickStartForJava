package com.wayneleo.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.persistence.Column;
import javax.persistence.Table;

/*-
 * 测试子类是否能够通过反射拿到父类的注解、属性、方法
 */
public class TestReflect {
    @Table( name = "TAB_A" )
    static class ParentBean {
        protected String id;

        @Column( name = "COL_A" )
        public String getId() {
            return id;
        }

        public void setId( String id ) {
            this.id = id;
        }
    }

    static class ChildBean extends ParentBean {
        public ChildBean() {
            setId( "wayne" );
        }
    }

    public static void main( String[] args ) {
        // 这里可以改
        Class<? extends ParentBean> clazz = ChildBean.class;
        Annotation[] annotations = clazz.getAnnotations();
        for ( int i = 0; i < annotations.length; i++ ) {
            System.out.println( "class annotation[" + i + "] : " + annotations[i].annotationType().getName() );
        }
        Field[] fields = clazz.getFields();
        for ( int i = 0; i < fields.length; i++ ) {
            System.out.println( "class field[" + i + "] : " + fields[i].getName() );
            Annotation[] fieldAnnotations = fields[i].getAnnotations();
            for ( int k = 0; k < fieldAnnotations.length; k++ ) {
                System.out.println(
                        "\tannotation[" + i + "] of field : " + fieldAnnotations[k].annotationType().getName() );
            }
        }
        Method[] methods = clazz.getMethods();
        for ( int i = 0; i < methods.length; i++ ) {
            System.out.println( "class method[" + i + "] : " + methods[i].getName() );
            Annotation[] methodAnnotations = methods[i].getAnnotations();
            for ( int k = 0; k < methodAnnotations.length; k++ ) {
                System.out.println(
                        "\tannotation[" + i + "] of method : " + methodAnnotations[k].annotationType().getName() );
            }
        }
    }
}
