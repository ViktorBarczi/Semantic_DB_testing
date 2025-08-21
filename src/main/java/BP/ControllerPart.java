package BP;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing")
public class ControllerPart {

  @Autowired
  private ServicePart service;

  private Utils utils;

  @GetMapping("/blazegraph/basic/{name}")
  public List<Output> blazegraphB(@PathVariable String name) {
    return service.queryBlazegraph(Utils.getBlazegraphUrl64MB(), Utils.getBasicQuery(name));
  }

  @GetMapping("/graphdb/basic/{name}")
  public List<Output> graphdbB(@PathVariable String name) {
    return service.queryGraphDB(Utils.getGraphdbUrl64MB(), Utils.getBasicQuery(name));
  }

  @GetMapping("/stardog/basic/{name}")
  public List<Output> stardogB(@PathVariable String name) {
    return service.queryStardog(Utils.getStardogUrl64MB(), Utils.getBasicQuery(name));
  }

  @GetMapping("/blazegraph/fts/{name}")
  public List<Output> blazegraphFTS(@PathVariable String name) {
    return service.queryBlazegraph(Utils.getBlazegraphUrl64MB(), Utils.getBlazegraphBDSQuery(name));
  }

  @GetMapping("/graphdb/fts/{name}")
  public List<Output> graphdbFTS(@PathVariable String name) {
    return service.queryGraphDB(Utils.getGraphdbUrl64MB(), Utils.getGraphDBLUCQuery(name));
  }

  @GetMapping("/stardog/fts/{name}")
  public List<Output> stardogFTS(@PathVariable String name) {
    return service.queryStardog(Utils.getStardogUrl64MB(), Utils.getStardogFTSQuery(name));
  }

  @GetMapping("/blazegraph/basic/{name}/big")
  public List<Output> blazegraphBBig(@PathVariable String name) {
    return service.queryBlazegraph(Utils.getBlazegraphUrl321MB(), Utils.getBasicQuery(name));
  }

  @GetMapping("/graphdb/basic/{name}/big")
  public List<Output> graphdbBBig(@PathVariable String name) {
    return service.queryGraphDB(Utils.getGraphdbUrl321MB(), Utils.getBasicQuery(name));
  }

  @GetMapping("/stardog/basic/{name}/big")
  public List<Output> stardogBBig(@PathVariable String name) {
    return service.queryStardog(Utils.getStardogUrl321MB(), Utils.getBasicQuery(name));
  }

  @GetMapping("/blazegraph/fts/{name}/big")
  public List<Output> blazegraphFTSBig(@PathVariable String name) {
    return service.queryBlazegraph(Utils.getBlazegraphUrl321MB(),
        Utils.getBlazegraphBDSQuery(name));
  }

  @GetMapping("/graphdb/fts/{name}/big")
  public List<Output> graphdbFTSBig(@PathVariable String name) {
    return service.queryGraphDB(Utils.getGraphdbUrl321MB(), Utils.getGraphDBLUCQuery(name));
  }

  @GetMapping("/stardog/fts/{name}/big")
  public List<Output> stardogFTSBig(@PathVariable String name) {
    return service.queryStardog(Utils.getStardogUrl321MB(), Utils.getStardogFTSQuery(name));
  }

  @GetMapping("/blazegraph/basic/{name}/oneGB")
  public List<Output> blazegraphBoneGB(@PathVariable String name) {
    return service.queryBlazegraph(Utils.getBlazegraphUrl1GB(), Utils.getBasicQuery(name));
  }

  @GetMapping("/graphdb/basic/{name}/oneGB")
  public List<Output> graphdbBoneGB(@PathVariable String name) {
    return service.queryGraphDB(Utils.getGraphdbUrl1GB(), Utils.getBasicQuery(name));
  }

  @GetMapping("/stardog/basic/{name}/oneGB")
  public List<Output> stardogBoneGB(@PathVariable String name) {
    return service.queryStardog(Utils.getStardogUrl1GB(), Utils.getBasicQuery(name));
  }

  @GetMapping("/blazegraph/fts/{name}/oneGB")
  public List<Output> blazegraphFTSoneGB(@PathVariable String name) {
    return service.queryBlazegraph(Utils.getBlazegraphUrl1GB(), Utils.getBlazegraphBDSQuery(name));
  }

  @GetMapping("/graphdb/fts/{name}/oneGB")
  public List<Output> graphdbFTSoneGB(@PathVariable String name) {
    return service.queryGraphDB(Utils.getGraphdbUrl1GB(), Utils.getGraphDBLUCQuery(name));
  }

  @GetMapping("/stardog/fts/{name}/oneGB")
  public List<Output> stardogFTSoneGB(@PathVariable String name) {
    return service.queryStardog(Utils.getStardogUrl1GB(), Utils.getStardogFTSQuery(name));
  }
}
