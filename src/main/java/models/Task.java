package models;

import resources.Resource;

/**
 * Created by Thomas on 04/11/2015.
 */
public class Task {
    public Class aClass;
    public String method;
    public Class[] parametersMethod;
    public Object[] parameters;

    /**
     * Keep the empty constructor for conversion json to the object
     */
    public Task() {
    }


    private Task(Class aClass, String method, Class[] parametersMethod, Object... parameters) {
        this.aClass = aClass;
        this.method = method;
        this.parametersMethod = parametersMethod;
        this.parameters = parameters;
    }

    public Task(Resource resource){
        this(resource.getaClass(), resource.getMethod(), resource.getParametersType(), resource.getParameters());
    }

    public void run() throws Exception {
//        System.out.println("Task.run() launched with : " + aClass.toString() + " / " + method + " / " + parametersMethod.length + " item in parametersMethod  / " + parameters.length + " parameters ");
        aClass.getMethod(method, parametersMethod).invoke(null, parameters);
    }
}
