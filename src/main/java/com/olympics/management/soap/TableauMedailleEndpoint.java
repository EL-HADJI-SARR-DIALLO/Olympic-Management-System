package com.olympics.management.soap;

import com.olympics.management.config.WebServiceConfig;
import com.olympics.management.dto.TableauMedailleResponse;
import com.olympics.management.service.TableauMedailleService;
import com.olympics.management.soap.generated.GetTableauMedaillesRequest;
import com.olympics.management.soap.generated.GetTableauMedaillesResponse;
import com.olympics.management.soap.generated.PaysMedaille;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class TableauMedailleEndpoint {

    private final TableauMedailleService tableauMedailleService;

    public TableauMedailleEndpoint(
            TableauMedailleService tableauMedailleService
    ) {
        this.tableauMedailleService = tableauMedailleService;
    }

    @PayloadRoot(
            namespace = WebServiceConfig.NAMESPACE_URI,
            localPart = "getTableauMedaillesRequest"
    )
    @ResponsePayload
    public GetTableauMedaillesResponse getTableauMedailles(
            @RequestPayload GetTableauMedaillesRequest request
    ) {

        List<TableauMedailleResponse> tableau =
                tableauMedailleService.obtenirTableauMedailles();

        GetTableauMedaillesResponse response =
                new GetTableauMedaillesResponse();

        for (TableauMedailleResponse ligne : tableau) {

            PaysMedaille pays = new PaysMedaille();

            pays.setRang(ligne.rang());
            pays.setNationalite(ligne.nationalite());
            pays.setNombreOr(ligne.nombreOr());
            pays.setNombreArgent(ligne.nombreArgent());
            pays.setNombreBronze(ligne.nombreBronze());
            pays.setTotal(ligne.total());

            response.getPays().add(pays);
        }

        return response;
    }
}