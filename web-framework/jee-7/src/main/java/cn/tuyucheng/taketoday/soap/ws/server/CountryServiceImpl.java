package cn.tuyucheng.taketoday.soap.ws.server;

import javax.jws.WebService;

@WebService(endpointInterface = "com.tuyucheng.soap.ws.server.CountryService")
public class CountryServiceImpl implements CountryService {

   private CountryRepository countryRepository = new CountryRepository();

   @Override
   public Country findByName(String name) {
      return countryRepository.findCountry(name);
   }

}
