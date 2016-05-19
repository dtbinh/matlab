function u = gram(v)
%Gram-Schmidt process
u = v;
u(:,1) = v(:,1);
for i = 2:size(v, 2)
    term = 0;
    for j = 1:(i - 1)
        term = term + proj(v(:,i), u(:,j)); 
    end
    u(:,i) = v(:,i) - term;
end
u = normc(u);
end
