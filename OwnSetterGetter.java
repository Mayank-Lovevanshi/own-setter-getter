import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.Class;

public class OwnSetterGetter
{

public static String argumentMaker(Field f)
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

public String setterGenerator(Field f[])
{
StringBuffer setterTemplate = new StringBuffer();
for(int i=0;i<f.length;i++)
{
String fieldName = f[i].getName();
String fieldDataType = f[i].getType().getSimpleName();
setterTemplate.append("public void ");
setterTemplate.append("set"+capitalize(fieldName));
setterTemplate.append(argumentMaker(f[i]));
setterTemplate.append("\n{\n");
setterTemplate.append("this."+fieldName+" = "+fieldName+";\n}");
setterTemplate.append("\n");
}
return setterTemplate.toString();
}

public String getterGenerator(Field f[])
{
StringBuffer getterTemplate = new StringBuffer();
for(int i=0;i<f.length;i++)
{
String fieldName = f[i].getName();
String fieldDataType = f[i].getType().getSimpleName();
getterTemplate.append("public "+fieldDataType+" get"+capitalize(fieldName)+"()\n{\n"+"return this."+fieldName+";\n}\n");
}
return getterTemplate.toString();
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

System.out.println(mk.getterGenerator(f));
System.out.println(mk.setterGenerator(f));
}
}
