package Controller;

import Service.SementeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class UsuarioController {

//exemplo aqui de um controlador REST com essas informações do REST vc tem as requisições web

    @RestController
    @RequestMapping("/api")
    public class Controller {

        private final SementeService sementeService;

        public Controller(SementeService sementeService) {
            this.sementeService = sementeService;
        }

        @GetMapping("/teste")
        public String mensagem() {
            return sementeService.toString();
        }

    }

}
