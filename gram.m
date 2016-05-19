function u = gram(v)
u = zeros(size(v));
u(:,1) = v(:,1);
for i = 2:size(v, 2)
    u(:,i) = v(:,i);
    for j = i:size(v, 2)
        u(:,i) = v(:,j);
    end
end
end
