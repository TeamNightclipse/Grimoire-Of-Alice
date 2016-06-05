package arekkuusu.grimoireOfAlice.tmp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.SOURCE)
@Target(value = {ElementType.TYPE, ElementType.TYPE_USE})
public @interface CleanupDone {}
