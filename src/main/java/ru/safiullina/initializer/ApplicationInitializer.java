package ru.safiullina.initializer;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;

public class ApplicationInitializer implements WebApplicationInitializer {

    /**
     * Tomcat ищет все классы, имплементирующие интерфейс: SpringServletContainerInitializer
     * И запускает на них метод onStartup.
     * Spring уже предоставляет готовую реализацию интерфейса SpringServletContainerInitializer,
     * которая ищет уже классы, имплементирующие
     * WebApplicationInitializer и запускает на них метод onStartup.
     *
     * @param servletContext - получает контекст
     */
    @Override
    public void onStartup(ServletContext servletContext) {
        // Создаем web контекст приложения
        final var context = new AnnotationConfigWebApplicationContext();
        // Указываем какой пакет сканировать
        context.scan("ru.safiullina");
        context.refresh();

        // Создаем диспетчер сервлет, который автоматически регистрирует обработчики запросов,
        // он же осуществляет диспетчеризацию
        final var servlet = new DispatcherServlet(context);
        // Регистрируем сервлет, app - будет его имя
        final var registration = servletContext.addServlet(
                "app", servlet
        );
        // Порядок загрузки, мы хотим чтобы наш сервлет инициализировался первым
        registration.setLoadOnStartup(1);
        // Указываем endpoint, которые будет обрабатывать наш сервлет, указываем корень, т.е. все запросы
        registration.addMapping("/");
    }
}
