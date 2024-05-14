package com.book.snow.acl.controller;



import com.book.snow.common.result.JsonResult;
import com.book.snow.model.dto.CreatePayment;
import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Product;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.ProductCreateParams;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "Stripe支付")
@RestController
@RequestMapping("/web/stripe/dev")
public class StripeController {

    private static Gson gson = new Gson();

    @GetMapping("/dev")
    public JsonResult start() throws StripeException {

        Stripe.apiKey = "sk_test_wU7nrJCZspk1NPDxiQgAF05q";

//        ==========      1        ============
//        ProductCreateParams productParams =
//                ProductCreateParams.builder()
//                        .setName("Starter Subscription")
//                        .setDescription("$12/Month subscription")
//                        .build();
//        Product product = Product.create(productParams);
//        System.out.println("Success! Here is your starter subscription product id: " + product.getId());
//        PaymentIntentCreateParams params =
//                PaymentIntentCreateParams.builder()
//                        .setAmount(2000L)
//                        .setCurrency("usd")
//                        .setAutomaticPaymentMethods(
//                                PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
//                                        .setEnabled(true)
//                                        .build()
//                        )
//                        .build();
//        Price price = Price.create(params);
//        System.out.println("Success! Here is your starter subscription price id: " + price.getId());
//        PaymentIntent paymentIntent = PaymentIntent.create(params);

        //===========2===============

        CreatePayment postBody = gson.fromJson(request.body(), CreatePayment.class);

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(new Long(calculateOrderAmount(postBody.getItems())))
                        .setCurrency("usd")
                        // In the latest version of the API, specifying the `automatic_payment_methods` parameter is optional because Stripe enables its functionality by default.
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods
                                        .builder()
                                        .setEnabled(true)
                                        .build()
                        )
                        .build();

        // Create a PaymentIntent with the order amount and currency
        PaymentIntent paymentIntent = PaymentIntent.create(params);

        CreatePaymentResponse paymentResponse = new CreatePaymentResponse(paymentIntent.getClientSecret());
        return gson.toJson(paymentResponse);

        return JsonResult.ok(paymentIntent);
    }
}
