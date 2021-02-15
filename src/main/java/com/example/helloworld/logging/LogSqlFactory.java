package com.example.helloworld.logging;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.SqlStatementCustomizer;
import org.skife.jdbi.v2.sqlobject.SqlStatementCustomizerFactory;
import org.skife.jdbi.v2.sqlobject.SqlStatementCustomizingAnnotation;
import org.skife.jdbi.v2.tweak.StatementCustomizer;

// https://stackoverflow.com/questions/23564383/how-to-print-the-sqlquery-annotation-in-jdbi-sql-api
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SqlStatementCustomizingAnnotation(LogSqlFactory.Factory.class)
public @interface LogSqlFactory {

    class Factory implements SqlStatementCustomizerFactory {

        private static void logSql(StatementContext context) {
            System.out.println("Raw SQL:\n" + context.getRawSql());
            System.out.println("Parsed SQL:\n" + context.getRewrittenSql());
            System.out.println("Bindings: \n" + context.getBinding());
        }

        @Override
        public SqlStatementCustomizer createForMethod(Annotation annotation, Class sqlObjectType, Method method) {
            return null;
        }

        @Override
        public SqlStatementCustomizer createForType(Annotation annotation, final Class sqlObjectType) {

            return sqlStatement -> sqlStatement.addStatementCustomizer(new StatementCustomizer() {
                @Override
                public void beforeExecution(PreparedStatement stmt, StatementContext ctx) throws SQLException {
                    System.out.println("Before execution:\n" + stmt.toString());
//                  logSql(sqlStatement.getContext());
                }

                @Override
                public void afterExecution(PreparedStatement stmt, StatementContext ctx) throws SQLException {
                    System.out.println("After execution:\n" + stmt.toString());
//                  logSql(sqlStatement.getContext());
                }

                @Override
                public void cleanup(StatementContext ctx) throws SQLException {
                }
            });

        }

        @Override
        public SqlStatementCustomizer createForParameter(Annotation annotation, Class sqlObjectType, Method method,
                                                         Object arg) {
            return null;
        }
    }
}