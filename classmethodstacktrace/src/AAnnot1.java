import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention( RetentionPolicy.RUNTIME )
public @interface AAnnot1 
{
	public String name();
	public String value() default "some value";
}
