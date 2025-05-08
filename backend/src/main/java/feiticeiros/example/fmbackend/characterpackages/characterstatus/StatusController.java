package feiticeiros.example.fmbackend.characterpackages.characterstatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/character-status")
public class StatusController {

    StatusEntity statusEntity = new StatusEntity();

    @Autowired
    private StatusService statusService;

    @PostMapping
    public void createStatus() {
        statusService.setStatusManual(statusEntity, 100, 100);
    }

}
