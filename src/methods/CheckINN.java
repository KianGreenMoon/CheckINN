package methods;

import unisoft.ws.FNSNDSCAWS2;
import unisoft.ws.FNSNDSCAWS2Port;
import unisoft.ws.fnsndscaws2.request.NdsRequest2;
import unisoft.ws.fnsndscaws2.response.NdsResponse2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckINN {
    //Переменные класса
    private byte response;
    private String inn;
    private String kpp;
    private String date;

    //Подключение к сервису
    private static FNSNDSCAWS2 service = new FNSNDSCAWS2();
    private static FNSNDSCAWS2Port port = service.getFNSNDSCAWS2Port();
    //Формулирование запроса и ответа
    private static NdsRequest2 ndsRequest = new NdsRequest2();
    private NdsRequest2.NP np_in = new NdsRequest2.NP();                //Ввод данных
    private NdsResponse2 ndsResponse;
    private NdsResponse2.NP np_out;                                     //Вывод данных

    private static int count = 0;
    private int id;

    //Конструктор
    public CheckINN(String inn, String kpp)
    {
        this.inn = editFormat(inn);
        this.kpp = editFormat(kpp);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        this.date = dateFormat.format(new Date());

        this.id = count;

        setRequest(this.inn, this.kpp, this.date);
        sendRequest();
        count++;
    }


    //Акцессоры
    public byte getResponse()
    {
        return this.response;
    }
    private NdsResponse2 getNdsResponse()
    {
       return ndsResponse;
    }
    private FNSNDSCAWS2Port getConnect()
    {
        return this.port;
    }
    private String editFormat(String format)
    {
        return format.replaceAll("[\\-\\s]", "").toUpperCase();
    }
    private int getId()
    {
        return this.id;
    }

    //Мутаторы
    private void setResponse(Byte state)
    {
        this.response = state;
    }
    private void setRequest(String inn, String kpp, String date)
    {
        np_in.setINN(inn);
        np_in.setKPP(kpp);
        np_in.setDT(date);
        ndsRequest.getNP().add(np_in);
    }
    private void setNdsResponse()
    {
        np_out = getNdsResponse().getNP().get(getId());
        setResponse(Byte.parseByte(this.np_out.getState()));
    }
    private void sendRequest(){
        //Запрос:
        ndsResponse = getConnect().ndsRequest2(ndsRequest);

        //Сохранить ответ:
        setNdsResponse();
    }
}
