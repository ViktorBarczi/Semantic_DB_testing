package BP;


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
  public String blazegraphB(@PathVariable String name) {
    return service.queryBlazegraph(Utils.getBlazegraphUrl64MB(), Utils.getBasicQuery(name));
  }

  @GetMapping("/graphdb/basic/{name}")
  public String graphdbB(@PathVariable String name) {
    return service.queryGraphDB(Utils.getGraphdbUrl64MB(), Utils.getBasicQuery(name));
  }

  @GetMapping("/stardog/basic/{name}")
  public String stardogB(@PathVariable String name) {
    return service.queryStardog(Utils.getStardogUrl64MB(), Utils.getBasicQuery(name));
  }

  @GetMapping("/blazegraph/fts/{name}")
  public String blazegraphFTS(@PathVariable String name) {
    return service.queryBlazegraph(Utils.getBlazegraphUrl64MB(), Utils.getBlazegraphBDSQuery(name));
  }

  @GetMapping("/graphdb/fts/{name}")
  public String graphdbFTS(@PathVariable String name) {
    return service.queryGraphDB(Utils.getGraphdbUrl64MB(), Utils.getGraphDBLUCQuery(name));
  }

  @GetMapping("/stardog/fts/{name}")
  public String stardogFTS(@PathVariable String name) {
    return service.queryStardog(Utils.getStardogUrl64MB(), Utils.getStardogFTSQuery(name));
  }

  @GetMapping("/blazegraph/basic/{name}/big")
  public String blazegraphBBig(@PathVariable String name) {
    return service.queryBlazegraph(Utils.getBlazegraphUrl321MB(), Utils.getBasicQuery(name));
  }

  @GetMapping("/graphdb/basic/{name}/big")
  public String graphdbBBig(@PathVariable String name) {
    return service.queryGraphDB(Utils.getGraphdbUrl321MB(), Utils.getBasicQuery(name));
  }

  @GetMapping("/stardog/basic/{name}/big")
  public String stardogBBig(@PathVariable String name) {
    return service.queryStardog(Utils.getStardogUrl321MB(), Utils.getBasicQuery(name));
  }

  @GetMapping("/blazegraph/fts/{name}/big")
  public String blazegraphFTSBig(@PathVariable String name) {
    return service.queryBlazegraph(Utils.getBlazegraphUrl321MB(),
        Utils.getBlazegraphBDSQuery(name));
  }

  @GetMapping("/graphdb/fts/{name}/big")
  public String graphdbFTSBig(@PathVariable String name) {
    return service.queryGraphDB(Utils.getGraphdbUrl321MB(), Utils.getGraphDBLUCQuery(name));
  }

  @GetMapping("/stardog/fts/{name}/big")
  public String stardogFTSBig(@PathVariable String name) {
    return service.queryStardog(Utils.getStardogUrl321MB(), Utils.getStardogFTSQuery(name));
  }
}
