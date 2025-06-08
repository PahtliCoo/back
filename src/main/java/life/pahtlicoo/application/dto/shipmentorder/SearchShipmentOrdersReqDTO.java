/**
 * Filter to obtain user shipments with optional filters.
 * @author Nicole Kapellmann Lepib (a01664563@tec.mx)
 * @since 2025-06-06
 */
package life.pahtlicoo.application.dto.shipmentorder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchShipmentOrdersReqDTO {
    private String name;
    private String date;  // YYYY-MM-DD
    private Integer state;
    private int page;
}

