import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.Class;

public class OwnSetterGetter
{

public String argumentMaker(Field f)
{
StringBuffer argumentTemplate = new StringBuffer();

argumentTemplate.append("(");
argumentTemplate.append(f.getType().getSimpleName());
argumentTemplate.append(" ");
argumentTemplate.append(f.getName());
argumentTemplate.append(")");

return argumentTemplate.toString();
}
public static String capitalize(String s) 
{
if (s == null || s.isEmpty()) return s;
return s.substring(0, 1).toUpperCase() + s.substring(1);
}


public static void main(String args[]) throws Exception
{
if(args.length!=1)
{
System.out.println("Usage : java OwnSetterGetter className ");
System.exit(0);
}
OwnSetterGetter mk = new OwnSetterGetter();
String className = args[0];
Class c = Class.forName(className);
Field f[] = c.getDeclaredFields();

StringBuffer SetterTemplate = new StringBuffer();
for(int i=0;i<f.length;i++)
{
String fieldName = f[i].getName();
String fieldDataType = f[i].getType().getSimpleName();
SetterTemplate.append("public void");
String methodName = "get"+capitalize(fieldName);
SetterTemplate.append(mk.argumentMaker(f[i]));
SetterTemplate.append("\n{\n");
SetterTemplate.append("this."+fieldName+" = "+fieldName+";\n}");
SetterTemplate.append("\n");
}
System.out.println(SetterTemplate.toString());
}
}
