package com.billy.billyServices.model;

import com.billy.billyServices.service.MonetaryService;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;
import org.hibernate.usertype.DynamicParameterizedType;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Currency;
import java.util.Properties;

/**
 * Class for Monetary amount user type (representation of MonetaryAmount in db)
 */
public class MonetaryAmountUserType implements CompositeUserType, DynamicParameterizedType {
    private static final String CONVERT_TO = "convertTo";
    private static final String USD = "USD";
    private static final String VALUE = "value";
    private static final String CURRENCY = "currency";
    private static final String EXCEPTION_MESSAGE = "MonetaryAmount is immutable";
    private static final int ZERO = 0;
    private static final int ONE = 1;

    private Currency convertTo;

    @Override
    public void setParameterValues(Properties properties) {

        final ParameterType parameterType = (ParameterType) properties.get(PARAMETER_TYPE);
        final String[] columns = parameterType.getColumns();
        final String table = parameterType.getTable();
        final Annotation[] annotations = parameterType.getAnnotationsMethod();

        final String convertToParameter = properties.getProperty(CONVERT_TO);
        this.convertTo = Currency.getInstance(
                convertToParameter != null ? convertToParameter : USD
        );
    }

    @Override
    public Class returnedClass() {
        return MonetaryAmount.class;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        return 0 == 01 || !(o == null || o1 == null) && o.equals(o1);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return o;
    }

    @Override // Better optimization if Hibernate knows class is not mutable
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o,
                                    SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
        return o.toString();
    }

    @Override
    public Object assemble(Serializable serializable,
                           SharedSessionContractImplementor sharedSessionContractImplementor,
                           Object o) throws HibernateException {
        return MonetaryAmount.fromString((String) serializable);
    }

    @Override
    public Object replace(Object o,
                          Object o1,
                          SharedSessionContractImplementor sharedSessionContractImplementor,
                          Object o2) throws HibernateException {
        return o;
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet,
                              String[] strings,
                              SharedSessionContractImplementor sharedSessionContractImplementor,
                              Object o) throws HibernateException, SQLException {

        final BigDecimal amount = resultSet.getBigDecimal(strings[ZERO]);
        if (resultSet.wasNull())
            return null;
        final Currency currency =
                Currency.getInstance(resultSet.getString(strings[ONE]));

        return new MonetaryAmount(amount, currency);
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement,
                            Object o,
                            int i,
                            SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {

        if (o == null) {
            preparedStatement.setNull(
                    i,
                    StandardBasicTypes.BIG_DECIMAL.sqlType()
            );
            preparedStatement.setNull(
                    i + 1,
                    StandardBasicTypes.CURRENCY.sqlType()
            );
        } else {
            final MonetaryAmount monetaryAmount = (MonetaryAmount) o;
            final MonetaryAmount dbMonetaryAmount = convert(monetaryAmount, convertTo);

            preparedStatement.setBigDecimal(i, dbMonetaryAmount.getAmount());
            preparedStatement.setString(i + 1, convertTo.getCurrencyCode());
        }
    }

    private MonetaryAmount convert(MonetaryAmount monetaryAmount, Currency toCurrency) {
        return new MonetaryAmount(
                monetaryAmount.getAmount(),
                toCurrency
        );
    }

    @Override
    public String[] getPropertyNames() {
        return new String[]{ VALUE, CURRENCY };
    }

    @Override
    public Type[] getPropertyTypes() {
        return new Type[] {
                StandardBasicTypes.BIG_DECIMAL,
                StandardBasicTypes.CURRENCY
        };
    }

    @Override
    public Object getPropertyValue(Object o, int i) throws HibernateException {

        final MonetaryAmount monetaryAmount = (MonetaryAmount) o;
        if (i == ZERO)
            return monetaryAmount.getAmount();
        else
            return monetaryAmount.getCurrency();
    }

    @Override
    public void setPropertyValue(Object o, int i, Object o1) throws HibernateException {

        throw new UnsupportedOperationException(
                EXCEPTION_MESSAGE
        );
    }
}
