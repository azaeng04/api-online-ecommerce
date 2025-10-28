package api.online.ecommerce.services;

import api.online.ecommerce.commons.CustomApiResponse;

public interface CustomerManagementService {
    CustomApiResponse<Object> getCustomerById(String customerId);
}
