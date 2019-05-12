package cz.lundegaard.entrytest.request;

import java.util.List;

public interface RequestService {

	List<RequestKind> listRequestKinds();

	void saveRequest(Request request);
}
