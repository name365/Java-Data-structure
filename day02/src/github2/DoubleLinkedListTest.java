package github2;

public class DoubleLinkedListTest {

	public static void main(String[] args) {
		// 测试一下
		// 1.创建节点
		HeroNode2 hero = new HeroNode2(1, "宋江", "及时雨");
		HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
		HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
		HeroNode2 hero4 = new HeroNode2(4, "公孙胜", "入云龙");
		HeroNode2 hero5 = new HeroNode2(5, "关胜", "大刀");

		// 创建一个双向链表
		DoubleLinkedList singk = new DoubleLinkedList();

		// 添加按照编号的顺序
		singk.add(hero);
		singk.add(hero4);
		singk.add(hero2);
		singk.add(hero5);
		singk.add(hero3);

		System.out.println("原来链表的情况:");
		singk.list(); // 显示链表

		// 修改
		HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "--");
		singk.update(newHeroNode);
		System.out.println("修改后的链表情况:");
		singk.list();

		// 删除
		singk.del(3);
		System.out.println("删除后的链表情况:");
		singk.list();
	}

}

// 创建一个双向链表的类
class DoubleLinkedList {
	// 先初始化一个头节点, 头节点不要动, 不存放具体的数据
	private HeroNode2 head = new HeroNode2(0, "", "");

	// 返回头节点
	public HeroNode2 getHead() {
		return head;
	}

	// 遍历双向链表的方法
	// 显示链表[遍历]
	public void list() {
		// 判断链表是否为空
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		// 由于头节点head不能动,所以需要一个辅助变量 temp,找到添加的位置
		HeroNode2 temp = head.next;
		while (true) {
			if (temp == null) { // 判断是否到链表最后
				break;
			}
			// 输出节点的信息
			System.out.println(temp);
			temp = temp.next; // 将temp后移
		}
	}

	// 添加一个节点到双向链表的最后.
	public void add(HeroNode2 heroNode) {
		// 由于头节点head不能动,所以需要一个辅助变量 temp
		HeroNode2 temp = head;
		// 遍历链表,找到最后
		while (true) {
			// 找到链表的最后
			if (temp.next == null) { // 判定找到了的条件
				break;
			}
			// 如果没有找到,将temp后移
			temp = temp.next;
		}
		// 当退出循环时,temp就指向了链表的最后
		// 将最后这个节点的next --指向--》 新的节点
		temp.next = heroNode;
		heroNode.pre = temp;
	}

	// 修改节点的信息,根据id编号来修改,即id编号不改
	// 说明
	// 1.根据newHeroNode 的 id 来修改即可
	public void update(HeroNode2 newHeroNode) {
		// 判断是否空
		if(head.next == null){	//链表为空
			System.out.println("链表为空！！！");
			return;
		}
		//找到需要修改的节点,根据 id 编号
		//定义一个临时变量
		HeroNode2 temp = head.next;
		boolean flag = false;	//表示是否找到该节点
		while(true){
			if(temp == null){
				break;	//已经遍历完链表
			}
			if(temp.id == newHeroNode.id){
				//找到了
				flag = true;
				break;
			}
			temp = temp.next;
		}
		// 根据flag 判断是否找到要修改的节点
		if (flag) {
			temp.name = newHeroNode.name;
			temp.nickName = newHeroNode.nickName;
		} else { // 没有找到
			System.out.printf("没有找到编号为 %d 的节点，不能修改。\n", newHeroNode.id);
		}
	}

	// 从双向链表中删除一个节点,
	// 说明
	// 1 对于双向链表，我们可以直接找到要删除的这个节点
	// 2 找到后，自我删除即可
	public void del(int id) {

		// 判断当前链表是否为空
		if (head.next == null) {// 空链表
			System.out.println("链表为空，无法删除");
			return;
		}

		HeroNode2 temp = head.next; // 辅助变量(指针)
		boolean flag = false; // 标志是否找到待删除节点的
		while (true) {
			if (temp == null) { // 已经到链表的最后
				break;
			}
			if (temp.id == id) {
				// 找到的待删除节点的前一个节点temp
				flag = true;
				break;
			}
			temp = temp.next; // temp后移，遍历
		}
		//根据flag 判断是否找到要修改的节点
		if (flag) { // 找到了
			// 可以删除
			// temp.next = temp.next.next;[单向链表]
			temp.pre.next = temp.next;
			// 这里我们的代码有问题?
			// 如果是最后一个节点，就不需要执行下面这句话，否则出现空指针
			if (temp.next != null) {
				temp.next.pre = temp.pre;
			}
		} else {
			System.out.printf("要删除的 %d 节点不存在\n", id);
		}
	}
}

// 定义HeroNode2 ， 每个HeroNode 对象就是一个节点
class HeroNode2 {
	public int id;
	public String name;
	public String nickName;
	public HeroNode2 next; // 指向下一个节点, 默认为null
	public HeroNode2 pre; // 指向前一个节点, 默认为null

	// 构造器
	public HeroNode2(int id, String name, String nickName) {
		super();
		this.id = id;
		this.name = name;
		this.nickName = nickName;
	}

	// 为了显示方便，重写toString方法
	@Override
	public String toString() {
		return "HeroNode2 [id=" + id + ", name=" + name + ", nickName=" + nickName + "]";
	}

}