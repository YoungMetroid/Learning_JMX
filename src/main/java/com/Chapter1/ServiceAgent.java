package com.Chapter1;

import javax.management.*;
import com.sun.jdmk.comm.*;

public class ServiceAgent {

    public static void main(String[] args)
    {
        try
        {
            MBeanServer mBeanServer = MBeanServerFactory.createMBeanServer();

            ObjectName objectName = new ObjectName("services:name=DateTim,type=information");

            mBeanServer.registerMBean(new DateTimeService(),objectName);

            ObjectName adaptorOName = new ObjectName("adaptors:protocol=HTTP");

            HtmlAdaptorServer htmlAdaptor = new HtmlAdaptorServer();
            mBeanServer.registerMBean(htmlAdaptor,adaptorOName);
            htmlAdaptor.start();
        }
        catch (MBeanRegistrationException  | NotCompliantMBeanException | MalformedObjectNameException | InstanceAlreadyExistsException exception)
        {
            exception.printStackTrace();
        }
    }
}
