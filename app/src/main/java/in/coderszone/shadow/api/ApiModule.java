package in.coderszone.shadow.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Vivek on 10/29/2017.
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    public ContactApiService apiService() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new Retrofit.Builder()
                .baseUrl("http://localhost:8080/service")
                .addConverterFactory(JacksonConverterFactory.create(mapper)).build()
                .create(ContactApiService.class);
    }
}