package cz.memsource.entrytest.request;

import java.util.List;

public interface RequestService {

	List<RequestKind> listRequestKinds();

	void saveRequest(Request request);
}
