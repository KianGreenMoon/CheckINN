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

    //Конструктор
    public CheckINN(String inn, String kpp)
    {
        this.inn = editFormat(inn);
        this.kpp = editFormat(kpp);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        this.date = dateFormat.format(new Date());
        sendRequest(this.inn, this.kpp, this.date);
    }


    //Акцессоры
    public byte getResponse()
    {
        return this.response;
    }
    private String editFormat(String format)
    {
        return format.replaceAll("[\\-\\s]", "").toUpperCase();
    }

    //Мутаторы
    private void setResponse(Byte state)
    {
        this.response = state;
    }
    private void sendRequest(String inn, String kpp, String date){
        //Запрос:
        FNSNDSCAWS2 service = new FNSNDSCAWS2();
        FNSNDSCAWS2Port port = service.getFNSNDSCAWS2Port();
        NdsRequest2.NP request2NP = new NdsRequest2.NP();
        request2NP.setINN(inn);
        request2NP.setKPP(kpp);
        request2NP.setDT(date);
        NdsRequest2 request2 = new NdsRequest2();
        request2.getNP().add(request2NP);
        NdsResponse2 response2 = port.ndsRequest2(request2);
        NdsResponse2.NP np_out = response2.getNP().get(0);

        //Сохранить ответ:
        setResponse(Byte.parseByte(np_out.getState()));
    }
}
