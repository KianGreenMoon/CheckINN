package methods;

import java.util.Date;

public class CheckINN {
    //Переменные класса
    private Integer response = null;
    private String inn;
    private String kpp;
    private Date date;

    //Конструктор
    public CheckINN(String inn, String kpp)
    {
        this.inn = editFormat(inn);
        this.kpp = editFormat(kpp);
        this.date = new Date();
        setResponse(inn, kpp);
    }


    //Акцессоры
    public Integer getResponse()
    {
        return this.response;
    }
    private String editFormat(String format)
    {
        return format.replaceAll("[\\-\\s]", "").toUpperCase();
    }

    //Мутаторы
    private void setResponse(String inn, String kpp)
    {
        sendRequest(inn, kpp);
    }
    private void sendRequest(String inn, String kpp){
        //Тут должен быть запрос к API. Но у меня нет к ней документации
    }
}