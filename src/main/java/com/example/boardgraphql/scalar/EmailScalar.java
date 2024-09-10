package com.example.boardgraphql.scalar;

import com.netflix.graphql.dgs.DgsScalar;
import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@DgsScalar(name = "Email")
public class EmailScalar implements Coercing<String, String> {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Override
    public String parseLiteral(Object input) throws CoercingParseLiteralException {
        if (input instanceof StringValue) {

            StringValue stringValue = (StringValue) input;
            String email = stringValue.getValue();
            if (isValidEmail(email)) {
                return email;
            }
        }

        throw new CoercingParseLiteralException("Value is not a valid Email");
    }


    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
}
