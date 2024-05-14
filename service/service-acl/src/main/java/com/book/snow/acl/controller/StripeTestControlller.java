package com.book.snow.acl.controller;


import com.book.snow.common.result.JsonResult;
import com.book.snow.model.dto.CreatePayment;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "stripeTest接口")
@RequestMapping("/web/stripe/test")
@RestController
public class StripeTestControlller {

    @PostMapping("/create-payment-intent")
    public JsonResult createPaymentIntent(@RequestBody CreatePayment createPayment)throws StripeException {
        PaymentIntentCreateParams createParams = new
                PaymentIntentCreateParams.Builder()
                .setCurrency("usd")
                .putMetadata("featureRequest", createPayment.getFeatureRequest())
                .setAmount(createPayment.getAmount() * 100L)
                .build();

        PaymentIntent intent = PaymentIntent.create(createParams);
        return JsonResult.ok(intent);
    }


}
