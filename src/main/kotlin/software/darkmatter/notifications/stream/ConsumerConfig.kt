package software.darkmatter.notifications.stream

import mu.KLogger
import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import software.darkmatter.model.notification.InAppNotification
import java.util.function.Function

@Configuration
class ConsumerConfig {

    private val logger: KLogger = KotlinLogging.logger { }

    @Bean
    fun notifications(): Function<Flux<InAppNotification>, Mono<Void>> =
        Function { eventFlux ->
            eventFlux.doOnNext {
                logger.info { "Received Event: $it" }
            }.then()
        }
}
