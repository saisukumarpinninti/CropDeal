package com.cropdeal;

import com.cropdeal.Repsitory.detailsrepo;
import com.cropdeal.Service.CropService;
import com.cropdeal.Service.SequenceGenerator;
import com.cropdeal.entity.PaymentCropDeal;
import com.cropdeal.entity.paytmdetails;
import com.paytm.pg.merchant.PaytmChecksum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

@Controller
public class PaymentController {

    @Autowired
    CropService cropservice;
    @Autowired
    private detailsrepo detailsrepo;
    @Autowired
    private paytmdetails paytmDetail;
    @Autowired
    private Environment env;
    @Autowired
    private SequenceGenerator sequenceGenerator;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping(value = "/submitPaymentDetail")
    public ModelAndView getRedirect(@RequestParam(name = "CUST_ID") String customerId,
                                    @RequestParam(name = "TXN_AMOUNT") String transactionAmount,
                                    @RequestParam(name = "CropId") String CropId) throws Exception {
        String orderId = sequenceGenerator.getSequenceNumber("Payment_Sequence");
        ModelAndView modelAndView = new ModelAndView("redirect:" + paytmDetail.getPaytmUrl());
        TreeMap<String, String> parameters = new TreeMap<>();
        paytmDetail.getDetails().forEach((k, v) -> parameters.put(k, v));
        parameters.put("MOBILE_NO", env.getProperty("paytm.mobile"));
        parameters.put("EMAIL", env.getProperty("paytm.email"));
        parameters.put("ORDER_ID", orderId);
        parameters.put("TXN_AMOUNT", transactionAmount);
        parameters.put("CUST_ID", customerId);
        String checkSum = getCheckSum(parameters);
        parameters.put("CHECKSUMHASH", checkSum);
        modelAndView.addAllObjects(parameters);
        PaymentCropDeal p = new PaymentCropDeal();
        p.setId(orderId);
        p.setCustomerId(customerId);
        p.setTransactionAmount(parameters.get("TXNAMOUNT"));
        p.setCropId(CropId);
        p.setTransactionId("Na");
        p.setStatus("In Process");
        detailsrepo.save(p);
        return modelAndView;

    }


    @PostMapping(value = "/pgresponse")
    public String getResponseRedirect(HttpServletRequest request, Model model) {
        Map<String, String[]> mapData = request.getParameterMap();
        TreeMap<String, String> parameters = new TreeMap<String, String>();

//		 	PaymentCropDeal p = detailsrepo.findById(parameters.get("ORDER_ID")).get();
        String paytmChecksum = "";
        for (Entry<String, String[]> requestParamsEntry : mapData.entrySet()) {

            if ("CHECKSUMHASH".equalsIgnoreCase(requestParamsEntry.getKey())) {
                paytmChecksum = requestParamsEntry.getValue()[0];
            } else {
                parameters.put(requestParamsEntry.getKey(), requestParamsEntry.getValue()[0]);
            }
        }
        String result;
        boolean isValideChecksum = false;
        try {
            isValideChecksum = validateCheckSum(parameters, paytmChecksum);
            if (isValideChecksum && parameters.containsKey("RESPCODE")) {
                if (parameters.get("RESPCODE").equals("01")) {
                    result = "Payment Successful";
                    System.out.println("check" + parameters.toString());
                    System.out.println(parameters.get("ORDERID"));
                    PaymentCropDeal p = detailsrepo.findById(parameters.get("ORDERID")).get();

                    cropservice.updateCrop(p.getCropId());
                } else {
                    result = "Payment Failed";
                }
            } else {
                result = "Checksum mismatched";
            }
        } catch (Exception e) {
            result = e.toString();
        }
        model.addAttribute("result", result);
        parameters.remove("CHECKSUMHASH");
        model.addAttribute("parameters", parameters);
        PaymentCropDeal p = detailsrepo.findById(parameters.get("ORDERID")).get();
        p.setTransactionId(parameters.get("TXNID"));
        p.setStatus(parameters.get("STATUS") + " " + parameters.get("RESPMSG") + " " + parameters.get(("RESPCODE")));
        detailsrepo.save(p);
        return "report";
    }

    private boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum) throws Exception {
        return PaytmChecksum.verifySignature(parameters,
                paytmDetail.getMerchantKey(), paytmChecksum);
    }


    private String getCheckSum(TreeMap<String, String> parameters) throws Exception {
        return PaytmChecksum.generateSignature(parameters, paytmDetail.getMerchantKey());
    }

}
