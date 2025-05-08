package com.ra.base_spring_boot.services;

import com.ra.base_spring_boot.dto.MessageResponse;
import com.ra.base_spring_boot.dto.ResponseWrapper;
import com.ra.base_spring_boot.dto.req.FormLogin;
import com.ra.base_spring_boot.dto.req.FormRegister;
import com.ra.base_spring_boot.dto.req.OtpDto;
import com.ra.base_spring_boot.dto.resp.JwtResponse;
import jakarta.mail.MessagingException;

public interface IAuthService
{

    void register(FormRegister request) throws MessagingException;

    ResponseWrapper<JwtResponse> login(FormLogin formLogin);

    MessageResponse verify(OtpDto otp);
}
