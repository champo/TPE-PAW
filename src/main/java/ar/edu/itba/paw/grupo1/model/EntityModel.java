package ar.edu.itba.paw.grupo1.model;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.util.Assert;

@SuppressWarnings("serial")
public class EntityModel<T> implements IModel<T> {
	
	private Class<T> type;
	private Integer id;
	
	private transient T value;
	private transient boolean attached;
	
	@SpringBean
	private transient EntityResolver resolver;
	
	public EntityModel(Class<T> type, Integer id) {
		super();
		Assert.notNull(type, "You must provide a type for entity resolver models!");
		Assert.notNull(id, "No id was provided to the entity model!");
		this.type = type;
		this.id = id;
	}
	
	public EntityModel(Class<T> type, T object) {
		super();
		Assert.notNull(type, "You must provide a type for entity resolver models!");
		this.type = type;
		this.id = (object == null ? null : resolver().getId(object));
		this.value = object;
		this.attached = true;
	}
	
	public EntityModel(Class<T> type) {
		this(type, (T)null);
	}

	protected T load() {
		if (id == null) {
			return null;
		}
		return resolver().fetch(type, id);
	}

	@Override
	public T getObject() {
		if (!attached) {
			value = load();
			attached = true;
		}
		return value;
	}

	@Override
	public void setObject(T object) {
		id = (object == null) ? null : resolver().getId(object);
		value = object;
		attached = true;
	}
	
	private EntityResolver resolver() {
		if (resolver == null) {
			Injector.get().inject(this);
			Assert.state(resolver != null, "Can't inject entity resolver!");
		}
		return resolver;
	}


	@Override
	public void detach() {
		if (attached) {
			value = null;
			attached = false;
		}
	}
}
