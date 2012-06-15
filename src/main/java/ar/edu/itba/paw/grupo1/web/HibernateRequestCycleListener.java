package ar.edu.itba.paw.grupo1.web;


import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ManagedSessionContext;
import org.springframework.util.Assert;

public class HibernateRequestCycleListener extends AbstractRequestCycleListener {

	private final SessionFactory sessionFactory;
	private boolean error;

	public HibernateRequestCycleListener(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void onBeginRequest(RequestCycle cycle) {
		Assert.state(!ManagedSessionContext.hasBind(sessionFactory), "Session already bound to this thread");
		Session session = sessionFactory.openSession();
		ManagedSessionContext.bind(session);
		session.beginTransaction();
	}
	
	@Override
	public void onEndRequest(RequestCycle cycle) {
		if (!error) {
			commit();
		} else {
			rollback();
		}
	}

	@Override
	public IRequestHandler onException(RequestCycle cycle, Exception ex) {
		rollback();
		error = true;
		return null;
	}
	
	private void commit() {
		Session session = sessionFactory.getCurrentSession();
		Assert.state(session.isOpen(), "Can't commit a closed session!");
		try {
			Transaction tx = session.getTransaction();
			if (tx.isActive()) {
				session.flush();
				tx.commit();
			}
		} finally {
			close(session);
		}
	}

	private void rollback() {
		Session session = sessionFactory.getCurrentSession();
		Assert.state(session.isOpen(), "Can't rollback a closed session!");
		try {
			Transaction tx = session.getTransaction();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			close(session);
		}
	}

	private void close(Session session) {
		ManagedSessionContext.unbind(sessionFactory);
		session.close();
	}
}
