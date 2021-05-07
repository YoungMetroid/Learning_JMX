package com.Chapter2;

import com.sun.jdmk.comm.HtmlAdaptorServer;
import javax.management.*;
import java.util.Set;

public class Main2 {
    public static void main(String[] args)
    {

        //QueryExp queryExpression = Query.initialSubString(new AttributeValueExp("Date"), new StringValueExp(""));
        DateTimeService dateTimeService = new DateTimeService();
        System.out.println(dateTimeService.getTime());
        System.out.println(dateTimeService.getDate());
        try
        {
            MBeanServer mBeanServer = MBeanServerFactory.createMBeanServer();

            ObjectName objectName = new ObjectName("services:name=DateTime,type=information");

            mBeanServer.registerMBean(new DateTimeService(),objectName);


            ObjectName objectName1 = new ObjectName("services:type=information,*");
            //Set queryList =mBeanServer.queryNames(objectName1,queryExpression);


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
