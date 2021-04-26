package com.Chapter1;

import javax.management.*;
import com.sun.jdmk.comm.*;

import java.util.Set;

public class ServiceAgent {

    public static void main(String[] args)
    {
        QueryExp queryExpression = Query.initialSubString(new AttributeValueExp("Date"), new StringValueExp(""));
        try
        {
            MBeanServer mBeanServer = MBeanServerFactory.createMBeanServer();

            ObjectName objectName = new ObjectName("services:name=DateTime,type=information");

            mBeanServer.registerMBean(new DateTimeService(),objectName);


            ObjectName objectName1 = new ObjectName("services:type=information,*");
            Set queryList =mBeanServer.queryNames(objectName1,queryExpression);


            MBeanInfo mBeanInfo = mBeanServer.getMBeanInfo(objectName);

            MBeanAttributeInfo[] attributeInfosArray = mBeanInfo.getAttributes();
            int attributesTotal = mBeanInfo.getAttributes().length;


            ObjectName adaptorOName = new ObjectName("adaptors:protocol=HTTP");

            HtmlAdaptorServer htmlAdaptor = new HtmlAdaptorServer();
            mBeanServer.registerMBean(htmlAdaptor,adaptorOName);


            htmlAdaptor.start();
        }
        catch (MBeanRegistrationException | NotCompliantMBeanException | MalformedObjectNameException |
                InstanceAlreadyExistsException | InstanceNotFoundException | IntrospectionException |
                ReflectionException exception)
        {
            exception.printStackTrace();
        }
    }
}
