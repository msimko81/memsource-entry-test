package cz.lundegaard.entrytest.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RequestServiceImpl implements RequestService {

	@Autowired
	private RequestKindDao requestKindDao;

	@Autowired
	private RequestDao requestDao;

	@Override
	@Cacheable("requestKinds")
	public List<RequestKind> listRequestKinds() {
		return requestKindDao.findAll();
	}

	@Override
	public void saveRequest(Request request) {
		log.debug("saving request: {}", request);
		requestDao.save(request);
	}
}
