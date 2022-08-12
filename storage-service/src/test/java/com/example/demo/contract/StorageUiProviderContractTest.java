package com.example.demo.contract;

import static org.mockito.Mockito.when;

import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Consumer;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import com.example.demo.entity.Item;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import com.example.demo.service.StorageService;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;

@Provider("StorageService")
@Consumer("StorageUi")
@PactFolder("contracts")
@Tag("contractTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StorageUiProviderContractTest {

  @LocalServerPort
  int port;

  @MockBean
  private StorageService storageService;

  @BeforeEach
  void setUp(PactVerificationContext context) {
    context.setTarget(new HttpTestTarget("localhost", port));
  }

  @TestTemplate
  @ExtendWith(PactVerificationInvocationContextProvider.class)
  void verifyPact(PactVerificationContext context) {
    context.verifyInteraction();
  }

  @State("returns all available items")
  void toItemsExistState() {
    when(storageService.getItemsAvailable()).thenReturn(
        Arrays.asList(new Item("A"), new Item("B"), new Item("C"))
    );
  }
}
