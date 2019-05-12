package ejb.classes;

import ejb.interfaces.SingletonBeanLocal;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

@LocalBean
@Singleton
public class SingletonBean implements SingletonBeanLocal {
int a;
@PostConstruct
public void Postconstruct()
{
a=0;
}
@Override
public int numbers()
{
    return a++;
}

    @Override
public String message(int b)
{
        a++;
        return "Ваш заказ №"+b+" успешно оформлен. Ожидайте звонка в течение 20 минут";
}

}
