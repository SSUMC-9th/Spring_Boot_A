package com.example.umc9th.global.resolver;

import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class PositivePageArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String PARAM_NAME = "page";
    private static final int DEFAULT_PAGE = 1;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PositivePage.class)
                && (parameter.getParameterType().equals(Integer.class)
                || parameter.getParameterType().equals(int.class));
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {

        String raw = webRequest.getParameter(PARAM_NAME);

        int page;
        if (raw == null || raw.isBlank()) {
            page = DEFAULT_PAGE;
        } else {
            try {
                page = Integer.parseInt(raw);
            } catch (NumberFormatException e) {
                throw new GeneralException(GeneralErrorCode.INVALID_PAGE);
            }
        }

        if (page <= 0) {
            throw new GeneralException(GeneralErrorCode.INVALID_PAGE);
        }

        // 프론트는 1부터, 서버는 0부터 사용
        return page - 1;
    }
}

