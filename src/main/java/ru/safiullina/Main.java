package ru.safiullina;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.IOException;
import java.nio.file.Files;

public class Main {

    public static void main(String[] args) throws LifecycleException, IOException {
        // Конфигурируем Tomcat: создаем объект класса Tomcat
        final var tomcat = new Tomcat();
        // Создаем для Томкэта временную директорию
        final var baseDir = Files.createTempDirectory("tomcat");
        // Директория будет временной
        baseDir.toFile().deleteOnExit();

        // Сеттером делаем настройку Томкэты
        tomcat.setBaseDir(baseDir.toAbsolutePath().toString());

        // Настраиваем порты через коннекторы, создаем коннектор
        final var connector = new Connector();
        // Устанавливаем порт
        connector.setPort(8080);
        // Присваиваем Томкэту коннектор
        tomcat.setConnector(connector);

        // Базовый путь к приложению будет текущая папка
        tomcat.getHost().setAppBase(".");
        // Контекст пустой и папка для документов тоже текущая
        tomcat.addWebapp("", ".");

        // Стартуем сервер и чтобы приложение не закрылось вызываем метод await()
        tomcat.start();
        tomcat.getServer().await();
    }


}
