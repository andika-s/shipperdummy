package com.shipperdummy.idgenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.boot.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;
import org.jboss.logging.Logger;

public class StringPrefixedSequenceIdUserGenerator extends SequenceStyleGenerator {

	protected Logger logger = Logger.getLogger(StringPrefixedSequenceIdUserGenerator.class.getName());

	public static final String CODE_FORMAT_PARAMETER = "codeinit";
	public static final String CODE_FORMAT_DEFAULT = "TK-SHPUSR-";
	private String codeFormat;

	public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
	public static final String NUMBER_FORMAT_DEFAULT = "%07d";
	private String numberFormat;

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		return codeFormat + DateFormatter() + String.format(numberFormat, super.generate(session, object));
	}

	private String DateFormatter() {

		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String formatDateTime = localDateTime.format(formatter);

		logger.info("[SHIPPER] Date Format 'yyyyMMddHHmmss' :" + formatDateTime);

		return formatDateTime;
	}

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		super.configure(LongType.INSTANCE, params, serviceRegistry);

		codeFormat = ConfigurationHelper.getString(CODE_FORMAT_PARAMETER, params, CODE_FORMAT_DEFAULT);
		numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER, params, NUMBER_FORMAT_DEFAULT);

	}

}
