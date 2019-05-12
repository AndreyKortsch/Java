package ejb.interfaces;

import javax.ejb.Local;

@Local
public interface SingletonBeanLocal {
    int numbers();
    String message(int a);
}

